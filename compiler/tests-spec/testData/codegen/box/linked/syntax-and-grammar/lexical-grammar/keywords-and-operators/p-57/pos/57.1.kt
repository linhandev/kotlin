// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 57 -> sentence 57
 * NUMBER: 1
 * DESCRIPTION: FIELD token in use-site annotation @field:JvmField on property
 */
// TESTCASE NUMBER: 1

class FieldHolder57 {
    @field:JvmField
    var counter = 0
}

fun box(): String {
    val holder = FieldHolder57()
    holder.counter = 1
    return if (holder.counter == 1) "OK" else "NOK"
}
