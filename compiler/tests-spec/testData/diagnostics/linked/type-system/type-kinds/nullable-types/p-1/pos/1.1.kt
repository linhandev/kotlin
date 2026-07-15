// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Nullable types T? hold values of type T or null
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int? = null
    val y: Int? = 1
    checkSubtype<Int?>(x)
    checkSubtype<Int?>(y)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val s: String? = "ok"
    val t: String? = null
    checkSubtype<String?>(s)
    checkSubtype<String?>(t)
}


// TESTCASE NUMBER: 3
fun case_3(x: Int?) {
    val copy: Int? = x
    checkSubtype<Int?>(copy)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val list: List<String>? = listOf("a")
    checkSubtype<List<String>?>(list)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val x: Any? = 42
    checkSubtype<Any?>(x)
}
