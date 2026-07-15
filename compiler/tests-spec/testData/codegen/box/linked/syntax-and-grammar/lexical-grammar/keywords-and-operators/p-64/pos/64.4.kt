// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 64 -> sentence 64
 * NUMBER: 4
 * DESCRIPTION: DELEGATE token on nested class delegated property with lazy
 */
// TESTCASE NUMBER: 1

class DelegateNested64 {
    inner class Inner {
        @delegate:Suppress("UNUSED")
        val flag: Boolean by lazy { true }
    }
}

fun box(): String {
    return if (DelegateNested64().Inner().flag) "OK" else "NOK"
}
