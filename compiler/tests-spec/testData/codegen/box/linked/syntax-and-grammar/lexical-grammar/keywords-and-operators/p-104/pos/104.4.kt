// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 104 -> sentence 104
 * NUMBER: 4
 * DESCRIPTION: PROTECTED token in protected inner class accessed from subclass
 */
// TESTCASE NUMBER: 1
open class ProtectedWrap104 {
    protected inner class Token104 {
        fun value(): String = "codegen-104-4"
    }

    protected fun create(): Token104 = Token104()
}

class ProtectedAccess104 : ProtectedWrap104() {
    fun read(): String = create().value()
}

fun box(): String = if (ProtectedAccess104().read() == "codegen-104-4") "OK" else "NOK"
