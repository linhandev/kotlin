// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 104 -> sentence 104
 * NUMBER: 2
 * DESCRIPTION: PROTECTED token in protected property of open class
 */
// TESTCASE NUMBER: 1
open class ProtectedProp104 {
    protected val token: String = "codegen-104-2"
}

class ProtectedReader104 : ProtectedProp104() {
    fun read(): String = token
}

fun box(): String = if (ProtectedReader104().read() == "codegen-104-2") "OK" else "NOK"
