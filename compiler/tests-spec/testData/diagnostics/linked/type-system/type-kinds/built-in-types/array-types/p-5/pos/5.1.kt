// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, array-types -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: kotlin.Array(T) is a parameterized type supporting get/set operations
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val arr = arrayOf("a", "b")
    checkSubtype<Array<String>>(arr)
    val x: String = arr[0]
    arr[0] = "c"
    arr.set(1, "d")
}


// TESTCASE NUMBER: 2
fun case_2() {
    val arr = arrayOf(1, 2, 3)
    checkSubtype<Array<Int>>(arr)
    val x: Int = arr[1]
    arr[1] = 10
    arr.set(2, 30)
}


// TESTCASE NUMBER: 3
class Case3

fun case_3() {
    val arr = arrayOf(Case3(), Case3())
    checkSubtype<Array<Case3>>(arr)
    val x: Case3 = arr[0]
    arr[0] = Case3()
}


// TESTCASE NUMBER: 4
fun case_4() {
    val arr: Array<String?> = arrayOf(null, "x")
    checkSubtype<Array<String?>>(arr)
    arr[0] = "y"
    arr.set(1, null)
}


// TESTCASE NUMBER: 5
fun case_5(): Array<Double> {
    val arr = arrayOf(1.0, 2.0)
    return arr
}

fun case_5_use(arr: Array<Double>) {
    checkSubtype<Array<Double>>(arr)
    arr[0] = arr[1]
}
