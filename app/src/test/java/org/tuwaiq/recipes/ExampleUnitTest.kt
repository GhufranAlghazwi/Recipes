package org.tuwaiq.recipes

import android.text.TextUtils
import android.util.Patterns
import androidx.core.util.PatternsCompat
import org.junit.Test

import org.junit.Assert.*
import org.tuwaiq.recipes.util.ValidatorHelper

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    fun validateEmailFormat(email: String): Boolean{
        var flag: Boolean
        if(email.isEmpty())
            flag = false
        var pattern = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        flag = pattern
        return flag
    }

    @Test
    fun validateEmail_isCorrect(){
        assertTrue(validateEmailFormat("Ghufran@gmail.com"))
        assertTrue(validateEmailFormat("Ghufran@Tuwaiq.edu.sa"))
        assertFalse(validateEmailFormat("Ghufran@gmail"))
        assertFalse(validateEmailFormat("Ghufran.com"))
        assertFalse(validateEmailFormat("Ghufran"))
        assertFalse(validateEmailFormat(""))
    }

    @Test
    fun validatePassword_isCorrect(){
        assertTrue(ValidatorHelper.validatePassword("12345678"))
        assertTrue(ValidatorHelper.validatePassword("abc12345"))
        assertFalse(ValidatorHelper.validatePassword("1234"))
        assertFalse(ValidatorHelper.validatePassword("abc111"))
        assertFalse(ValidatorHelper.validatePassword(""))
    }
}