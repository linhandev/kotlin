// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 76 -> sentence 76
 * NUMBER: 2
 * DESCRIPTION: COMPANION token in named companion object declaration
 */
// TESTCASE NUMBER: 1

class NamedCompanion76 {
    companion object Factory {
        fun create(): NamedCompanion76 = NamedCompanion76()
    }
}

fun box(): String {
    return if (NamedCompanion76.Factory.create() is NamedCompanion76) "OK" else "NOK"
}
