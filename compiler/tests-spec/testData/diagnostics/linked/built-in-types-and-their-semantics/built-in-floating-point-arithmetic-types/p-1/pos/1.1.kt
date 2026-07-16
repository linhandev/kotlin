// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-floating-point-arithmetic-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: constant-literals, real-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.Float and kotlin.Double literals, nullable variants and kotlin.Comparable subtyping
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val f: Float = 1.0f
    val d: Double = 1.0
    checkSubtype<Comparable<Float>>(f)
    checkSubtype<Comparable<Double>>(d)
    f.compareTo(2.0f) checkType { check<Int>() }
    d.compareTo(2.0) checkType { check<Int>() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val nf: Float? = null
    val nd: Double? = null
    checkSubtype<Float?>(nf)
    checkSubtype<Double?>(nd)
}


// TESTCASE NUMBER: 3
fun case_3(f: Float, d: Double) {
    checkSubtype<Comparable<Float>>(f)
    checkSubtype<Comparable<Double>>(d)
}
