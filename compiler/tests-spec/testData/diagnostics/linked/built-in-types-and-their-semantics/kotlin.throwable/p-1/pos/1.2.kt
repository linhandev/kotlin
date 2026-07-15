// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.throwable -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: kotlin.Throwable nullable references and nested cause chain
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val t: Throwable? = null
    checkSubtype<Throwable?>(t)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val root = Exception("root", IllegalArgumentException("cause"))
    root.cause checkType { check<Throwable?>() }
    root.cause?.message checkType { check<String?>() }
}


// TESTCASE NUMBER: 3
fun case_3(t: Throwable) {
    val message: String? = t.message
    checkSubtype<String?>(message)
}
