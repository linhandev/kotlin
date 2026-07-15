// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, nullable-types, nullability-lozenge -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Nullability lozenge subtyping allows non-null types to be assigned to nullable supertypes
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int = 1
    val y: Int? = x
    checkSubtype<Int?>(y)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val s: String = "hello"
    val t: String? = s
    checkSubtype<String?>(t)
}


// TESTCASE NUMBER: 3
fun <T> case_3(x: T) {
    val assign: T? = x
    checkSubtype<T?>(assign)
}


// TESTCASE NUMBER: 4
fun <T : Any> case_4(x: T) {
    val nullable: T? = x
    checkSubtype<T?>(nullable)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val x: Number = 42
    val y: Number? = x
    checkSubtype<Number?>(y)
}
