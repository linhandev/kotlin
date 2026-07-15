// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Bounded type parameters specify upper bounds for type parameters of type constructors
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Bounded1<T : Number>(val n: T)


// TESTCASE NUMBER: 2
class Multi2<T> where T : Comparable<T>, T : CharSequence


// TESTCASE NUMBER: 3
class Serializable3<T> where T : Cloneable, T : java.io.Serializable


// TESTCASE NUMBER: 4
fun case_4() {
    val b: Bounded1<Int> = Bounded1(42)
    val n: Number = b.n
    checkSubtype<Number>(n)
}


// TESTCASE NUMBER: 5
interface BoundParam5<S, T> where T : S, S : Number
