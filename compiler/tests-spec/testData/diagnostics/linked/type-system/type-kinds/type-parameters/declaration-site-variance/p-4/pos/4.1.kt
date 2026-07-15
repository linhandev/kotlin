// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, declaration-site-variance -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Type parameters may be invariant, covariant or contravariant at declaration site
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Invariant1<A>


// TESTCASE NUMBER: 2
interface Out2<out A>


// TESTCASE NUMBER: 3
interface In3<in A>


// TESTCASE NUMBER: 4
class Box4<out T>(val value: T)


// TESTCASE NUMBER: 5
interface Consumer5<in T> {
    fun accept(value: T)
}
