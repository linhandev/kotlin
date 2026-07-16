// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 118 -> sentence 118
 * NUMBER: 2
 * DESCRIPTION: ABSTRACT token in abstract class with abstract property
 */
// TESTCASE NUMBER: 1
abstract class Holder118 {
    abstract val token118: String
}

class HolderImpl118 : Holder118() {
    override val token118: String = "codegen-118-2"
}

fun box(): String = if (HolderImpl118().token118 == "codegen-118-2") "OK" else "NOK"
