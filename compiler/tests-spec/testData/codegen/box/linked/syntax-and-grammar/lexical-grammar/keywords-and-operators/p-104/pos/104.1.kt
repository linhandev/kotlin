// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 104 -> sentence 104
 * NUMBER: 1
 * DESCRIPTION: PROTECTED token in protected function of open class
 */
// TESTCASE NUMBER: 1
open class ProtectedBase104 {
    protected fun value(): String = "codegen-104-1"
}

class ProtectedDerived104 : ProtectedBase104() {
    fun read(): String = value()
}

fun box(): String = if (ProtectedDerived104().read() == "codegen-104-1") "OK" else "NOK"
