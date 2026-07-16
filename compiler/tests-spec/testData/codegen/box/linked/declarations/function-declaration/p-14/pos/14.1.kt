// WITH_STDLIB
// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: multiple spread operators and mixed arguments at runtime
 */

// TESTCASE NUMBER: 1
fun foo(vararg i: Int): IntArray = i

fun box(): String {
    val whole = foo(*intArrayOf(1, 2, 3))
    val mixed = foo(1, 2, *intArrayOf(3, 4), 5)
    val manySpreads = foo(*intArrayOf(1, 2, 3), 4, *intArrayOf(5, 6))
    return if (
        whole.contentEquals(intArrayOf(1, 2, 3)) &&
        mixed.contentEquals(intArrayOf(1, 2, 3, 4, 5)) &&
        manySpreads.contentEquals(intArrayOf(1, 2, 3, 4, 5, 6))
    ) {
        "OK"
    } else {
        "NOK whole=${whole.contentToString()} mixed=${mixed.contentToString()} many=${manySpreads.contentToString()}"
    }
}
