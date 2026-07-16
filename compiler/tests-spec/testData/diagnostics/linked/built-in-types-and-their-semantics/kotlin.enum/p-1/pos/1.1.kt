// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.enum -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: enum constants have the declaring enum type and kotlin.Comparable subtyping
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }
fun case_1() {
    val c: Color = Color.RED
    checkSubtype<Comparable<Color>>(c)
    c checkType { check<Color>() }
    c.compareTo(Color.GREEN) checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val nc: Color? = null
    checkSubtype<Color?>(nc)
}


// TESTCASE NUMBER: 3
fun case_3(c: Color) {
    checkSubtype<Comparable<Color>>(c)
    when (c) {
        Color.RED -> {}
        Color.GREEN -> {}
        Color.BLUE -> {}
    }
}
