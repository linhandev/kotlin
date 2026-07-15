// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Jump expressions have kotlin.Nothing type and never complete normally
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    loop@ for (i in 1..3) {
        val x = i
        break@loop
        <!UNREACHABLE_CODE!>val y = x<!>
        <!UNREACHABLE_CODE!>y<!>
    }
}


// TESTCASE NUMBER: 2
fun case_2() {
    for (i in 1..3) {
        continue
        <!UNREACHABLE_CODE!>i<!>
        <!UNREACHABLE_CODE!>val z = i<!>
    }
}


// TESTCASE NUMBER: 3
fun case_3(): Int {
    return 1
    <!UNREACHABLE_CODE!>return 2<!>
    <!UNREACHABLE_CODE!>3<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    listOf(1, 2).forEach lit@{
        return@lit
        <!UNREACHABLE_CODE!>print(it)<!>
    }
}


// TESTCASE NUMBER: 5
fun case_5() {
    for (k in 1..2) {
        val s = k.toString().takeIf { it.isNotEmpty() } ?: break
        checkSubtype<String>(s)
    }
}
