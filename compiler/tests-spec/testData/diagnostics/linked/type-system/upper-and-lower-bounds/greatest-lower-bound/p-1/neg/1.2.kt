// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds, greatest-lower-bound -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Unrelated function and collection types do not share a common GLB for assignment
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface GA
interface GB

fun case_1(x: GA) {
    val b: GB = <!TYPE_MISMATCH!>x<!>
}


// TESTCASE NUMBER: 2
fun case_2(x: GB) {
    val a: GA = <!TYPE_MISMATCH!>x<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val f: (Int) -> String = { it.toString() }
    val g: (String) -> Int = { it.length }
    val h: (Int) -> Int = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val list: List<Int> = listOf(1)
    val set: Set<Int> = <!TYPE_MISMATCH!>list<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val map: Map<Int, String> = mapOf(1 to "a")
    val pair: Pair<Int, String> = <!TYPE_MISMATCH!>map<!>
}
