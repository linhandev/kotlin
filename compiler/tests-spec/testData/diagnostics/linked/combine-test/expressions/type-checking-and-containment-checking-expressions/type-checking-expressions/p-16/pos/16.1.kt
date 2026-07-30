// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 16 -> sentence 16
 *                type-inference, smart-casts -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: bare type is check with type-parameterized member access type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Container<T>
class Box<T>(val items: List<T>) : Container<T>

fun case1() {
    val c: Container<Int> = Box(listOf(1, 2, 3))
    if (c is Box) {
        checkSubtype<List<Int>>(c.items)
        checkSubtype<Int>(c.items.sum())
    }
}
