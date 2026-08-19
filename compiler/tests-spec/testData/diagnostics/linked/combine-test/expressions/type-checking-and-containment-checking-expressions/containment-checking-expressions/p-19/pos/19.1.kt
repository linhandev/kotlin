// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: in operator on smart-cast non-null Box receiver infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val list: List<Int>?)

operator fun Box.contains(x: Int): Boolean = x in (list ?: emptyList())

fun case1(box: Box?) {
    if (box != null) {
        checkSubtype<Boolean>(5 in box)
    }
}

fun case2(box: Box?) {
    checkSubtype<Boolean>(if (box != null) 5 in box else false)
}
