// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.nothing -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, subtyping, subtyping-rules -> paragraph 2 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: kotlin.Nothing is a subtype via assignment, nullable types, and function types
 * HELPERS: checkType
 */

class CustomClass
interface CustomInterface
enum class CustomEnum { V }

fun fail(): Nothing = throw Exception()

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(fail())
}

// TESTCASE NUMBER: 2
fun case_2() {
    val x: String = fail()
    checkSubtype<String>(x)
}

// TESTCASE NUMBER: 3
fun case_3() {
    val y: CustomClass = fail()
    checkSubtype<CustomClass>(y)
}

// TESTCASE NUMBER: 4
fun case_4() {
    checkSubtype<String?>(fail())
}

// TESTCASE NUMBER: 5
fun case_5() {
    checkSubtype<(Int) -> Unit>(fail())
}
