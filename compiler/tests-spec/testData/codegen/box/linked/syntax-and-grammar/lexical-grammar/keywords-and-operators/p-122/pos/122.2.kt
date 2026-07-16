// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 122 -> sentence 122
 * NUMBER: 2
 * DESCRIPTION: LATEINIT token in lateinit var with isInitialized check
 */
// TESTCASE NUMBER: 1
class LateInitCheck122 {
    lateinit var flag122: String

    fun ready122(): String {
        if (::flag122.isInitialized) return flag122
        return "NOK"
    }
}

fun box(): String {
    val expected = "lateinit-122-2"
    val holder = LateInitCheck122()
    holder.flag122 = expected
    if (holder.ready122() != expected) return "NOK"
    return "OK"
}
