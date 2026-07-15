// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, subtyping, subtyping-rules -> paragraph 2 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Well-formed classifier type constructors and parameterized types T[A1, ..., An]
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Generic1<A, B>


// TESTCASE NUMBER: 2
interface Generic2<A, B>

interface ConcreteDerived2<P, Q> : Generic2<Int, String>


// TESTCASE NUMBER: 3
interface Generic3<P, Q>

interface GenericDerived3<P, Q> : Generic3<P, Q>


// TESTCASE NUMBER: 4
interface Out4<out A>

interface In4<in A>


// TESTCASE NUMBER: 5
interface NumberWrapper5<S : Number>

interface IntWrapper5 : NumberWrapper5<Int>
