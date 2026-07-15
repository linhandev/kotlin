// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, array-types -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: kotlin.Array(T) of custom and nullable element types supports get/set
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val arr = arrayOfNulls<String>(2)
    checkSubtype<Array<String?>>(arr)
    arr[0] = "test"
    val value = arr.get(0)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val arr = emptyArray<Long>()
    checkSubtype<Array<Long>>(arr)
    val size: Int = arr.size
}


// TESTCASE NUMBER: 3
fun case_3() {
    val arr = Array<Int>(3) { it * 2 }
    checkSubtype<Array<Int>>(arr)
    arr[0] = 1
    arr[2] = arr[1]
}


// TESTCASE NUMBER: 4
fun case_4(value: String): Array<String> {
    return arrayOf(value)
}

fun case_4_use() {
    val arr = case_4("test")
    checkSubtype<Array<String>>(arr)
    arr[0] = "updated"
}


// TESTCASE NUMBER: 5
fun case_5() {
    val arr: Array<out String> = arrayOf("a", "b")
    checkSubtype<Array<out String>>(arr)
    val x: String = arr[0]
}
