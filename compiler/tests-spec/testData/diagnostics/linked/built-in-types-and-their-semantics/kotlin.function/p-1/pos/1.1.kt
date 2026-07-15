// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.function -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: function types are subtypes of kotlin.Function with matching arity
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(f: (Int) -> String) {
    checkSubtype<Function1<Int, String>>(f)
    f(1) checkType { check<String>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f: () -> Unit = {}
    checkSubtype<Function0<Unit>>(f)
    f() checkType { check<Unit>() }
}


// TESTCASE NUMBER: 3
fun case_3(f: (Int, String) -> Boolean) {
    checkSubtype<Function2<Int, String, Boolean>>(f)
    f.invoke(1, "x") checkType { check<Boolean>() }
}
