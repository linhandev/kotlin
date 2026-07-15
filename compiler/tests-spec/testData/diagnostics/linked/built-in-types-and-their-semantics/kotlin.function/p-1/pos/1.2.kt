// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.function -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Function invoke and nullable function references
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
fun case_fn(x: Int): String = x.toString()
fun case_1() {
    val ref: (Int) -> String = ::case_fn
    ref checkType { check<(Int) -> String>() }
    ref(1) checkType { check<String>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val nf: ((Int) -> String)? = null
    checkSubtype<((Int) -> String)?>(nf)
}


// TESTCASE NUMBER: 3
fun case_3(f: Function1<Int, String>) {
    f.invoke(2) checkType { check<String>() }
}
