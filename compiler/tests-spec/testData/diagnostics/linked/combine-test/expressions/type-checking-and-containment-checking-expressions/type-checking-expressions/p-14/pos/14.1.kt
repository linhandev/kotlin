// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 14 -> sentence 14
 *                type-system, subtyping, subtyping-rules -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: precise type inference for bare type is-check on class hierarchy
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base<T>
class Derived<T>(val t: T) : Base<T>()

fun case_14() {
    val base: Base<Int> = Derived(42)
    if (base is Derived) {
        base checkType { check<Derived<Int>>() }
        checkSubtype<Derived<Int>>(base)
        checkSubtype<Int>(base.t)
    }
}
