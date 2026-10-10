package com.fragiorgis.rachaconta

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.UiController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fragiorgis.rachaconta.model.Conta
import com.fragiorgis.rachaconta.navigation.NavegacaoConta
import com.fragiorgis.rachaconta.ui.DividirContaActivity
import com.fragiorgis.rachaconta.ui.ResultadoActivity
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FluxoTest {
    private fun checkPessoa(id: Int) = allOf(withId(R.id.paid_check),
        isDescendantOfA(allOf(withId(R.id.person_row), hasDescendant(withText("Pessoa " + id)))))

    private fun resultado(conta: Conta): Intent = NavegacaoConta.resultado(ApplicationProvider.getApplicationContext<Context>(), conta)

    private fun preencherConta() {
        onView(withId(R.id.amount_input)).perform(scrollTo(), replaceText("100,00"), closeSoftKeyboard())
        onView(withId(R.id.more_button)).perform(scrollTo(), click())
        onView(withId(R.id.name_input)).perform(scrollTo(), replaceText("Pizza de sexta"), closeSoftKeyboard())
    }

    @Test fun entradaManualNavegaConfirmaEVoltaMantendoDados() {
        ActivityScenario.launch(DividirContaActivity::class.java).use {
            preencherConta()
            onView(withId(R.id.name_input)).perform(pressImeActionButton())
            onView(withId(R.id.total_value)).check(matches(withText("R$ 100,00")))
            onView(withId(R.id.paid_count)).check(matches(withText("0 de 3 pagos")))
            onView(checkPessoa(1)).perform(click())
            onView(withId(R.id.paid_count)).check(matches(withText("1 de 3 pagos")))
            onView(checkPessoa(1)).perform(click())
            onView(withId(R.id.paid_count)).check(matches(withText("0 de 3 pagos")))
            onView(withId(R.id.edit_button)).perform(click())
            onView(withId(R.id.amount_input)).check(matches(withText("100,00")))
            onView(withId(R.id.name_input)).check(matches(withText("Pizza de sexta")))
            onView(withId(R.id.people_count)).check(matches(withText("3")))
        }
    }
    @Test fun pagamentoSobreviveARecriacao() {
        ActivityScenario.launch<ResultadoActivity>(resultado(Conta(10000, 3, "Pizza de sexta"))).use { scenario ->
            onView(checkPessoa(2)).perform(click())
            scenario.recreate()
            onView(checkPessoa(2)).check(matches(isChecked()))
            onView(checkPessoa(1)).check(matches(isNotChecked()))
            onView(withId(R.id.paid_count)).check(matches(withText("1 de 3 pagos")))
        }
    }
    @Test fun nomeOpcionalEParcelaZeroSaoTratados() {
        ActivityScenario.launch<ResultadoActivity>(resultado(Conta(1, 3))).use {
            onView(withId(R.id.meeting_title)).check(matches(withText("Conta da turma")))
            onView(withId(R.id.total_value)).check(matches(withText("R$ 0,01")))
            onView(checkPessoa(3)).perform(click())
            onView(withId(R.id.paid_count)).check(matches(withText("1 de 3 pagos")))
        }
    }
    @Test fun formularioPreservaCamposNaRecriacao() {
        ActivityScenario.launch(DividirContaActivity::class.java).use { scenario ->
            preencherConta()
            scenario.recreate()
            onView(withId(R.id.amount_input)).check(matches(withText("100,00")))
            onView(withId(R.id.name_input)).check(matches(withText("Pizza de sexta")))
            onView(withId(R.id.people_count)).check(matches(withText("3")))
        }
    }
    @Test fun entradaInvalidaNaoAvanca() {
        ActivityScenario.launch(DividirContaActivity::class.java).use {
            onView(withId(R.id.divide_button)).perform(scrollTo(), click())
            onView(withText(R.string.invalid_amount)).check(matches(isDisplayed()))
            onView(withId(R.id.amount_input)).perform(replaceText("100000"), closeSoftKeyboard())
            onView(withId(R.id.divide_button)).perform(scrollTo(), click())
            onView(withText(R.string.invalid_amount)).check(matches(isDisplayed()))
        }
    }
    @Test fun cinquentaPessoasNaoTrocamChecksAoReciclar() {
        ActivityScenario.launch<ResultadoActivity>(resultado(Conta(9999999, 50, "Encontro da turma de programação mobile"))).use {
            onView(checkPessoa(1)).perform(click())
            onView(withId(R.id.people_list)).perform(irPara(49))
            onView(checkPessoa(50)).check(matches(isNotChecked())).perform(click())
            onView(withId(R.id.paid_count)).check(matches(withText("2 de 50 pagos")))
            onView(withId(R.id.people_list)).perform(irPara(0))
            onView(checkPessoa(1)).check(matches(isChecked()))
            onView(checkPessoa(2)).check(matches(isNotChecked()))
        }
    }
    private fun irPara(posicao: Int) = object : ViewAction {
        override fun getDescription() = "Rolar para pessoa por posição"
        override fun getConstraints() = isAssignableFrom(RecyclerView::class.java)
        override fun perform(ui: UiController, view: View) {
            (view as RecyclerView).scrollToPosition(posicao)
            ui.loopMainThreadUntilIdle()
        }
    }
    @Test fun limiteInferiorDesabilitaMenos() {
        ActivityScenario.launch(DividirContaActivity::class.java).use {
            onView(withId(R.id.less_button)).perform(scrollTo(), click())
            onView(withId(R.id.people_count)).check(matches(withText("1")))
            onView(withId(R.id.less_button)).check(matches(isNotEnabled()))
        }
    }
}
