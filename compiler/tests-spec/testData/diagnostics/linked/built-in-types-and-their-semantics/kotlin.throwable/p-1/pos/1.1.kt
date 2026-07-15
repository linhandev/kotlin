// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.throwable -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: exception classes are subtypes of kotlin.Throwable and usable in throw and catch
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
class Case1Exception : Exception("case1")

fun case_1() {
    val t: Throwable = Case1Exception()
    checkSubtype<Throwable>(t)
    throw t
}


// TESTCASE NUMBER: 2
fun case_2() {
    try {
        throw IllegalStateException("case2")
    } catch (e: Throwable) {
        e checkType { check<Throwable>() }
        e.message checkType { check<String?>() }
        e.cause checkType { check<Throwable?>() }
    }
}


// TESTCASE NUMBER: 3
fun case_3(e: RuntimeException) {
    checkSubtype<Throwable>(e)
    e.cause checkType { check<Throwable?>() }
}
