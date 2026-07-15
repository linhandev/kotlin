// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.unit -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: explicit empty return and Unit expression forms
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Unit {
    return
}

fun case_1_use() {
    case_1() checkType { check<Unit>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f = lit@{
        return@lit
    }
    f() checkType { check<Unit>() }
}


// TESTCASE NUMBER: 3
fun case_3(): Unit {
    return Unit
}

fun case_3_use() {
    case_3() checkType { check<Unit>() }
}


// TESTCASE NUMBER: 4
fun case_4() {
    val u = run {
        val proc = "case 4"
        Unit
    }
    u checkType { check<Unit>() }
}


// TESTCASE NUMBER: 5
fun case_5() {
    fun inner(): Unit {
        if (true) return
    }
    inner() checkType { check<Unit>() }
}
