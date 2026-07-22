// WITH_STDLIB
// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: vararg spread and named spread at runtime
 */

// TESTCASE NUMBER: 1
fun join(vararg items: Int): String = items.joinToString("-")

fun box(): String {
    val arr = intArrayOf(2, 3)
    val spread = join(*arr)
    val named = join(items = *arr)
    return if (spread == "2-3" && named == "2-3") "OK" else "NOK spread=$spread named=$named"
}
