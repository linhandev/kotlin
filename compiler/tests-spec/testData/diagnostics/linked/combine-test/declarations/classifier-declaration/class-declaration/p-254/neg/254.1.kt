// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 254 -> sentence 254
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 254 -> sentence 254
 *                type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 254 -> sentence 254
 *                inheritance, inheriting -> paragraph 254 -> sentence 254
 * NUMBER: 1
 * DESCRIPTION: implementing a generic interface with the wrong number of type arguments fails (WRONG_NUMBER_OF_TYPE_ARGUMENTS) and override is rejected (NOTHING_TO_OVERRIDE); contrasts with p-231/p-244 correct arity and with single-feature parameterized-classifier-types arity checks
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

class TooMany : Box<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><Int, String><!> {
    <!NOTHING_TO_OVERRIDE!>override<!> fun get(): Int = 1
}

// TESTCASE NUMBER: 2
interface PairLike<K, V> {
    fun key(): K
    fun value(): V
}

class TooFew : PairLike<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><Int><!> {
    <!NOTHING_TO_OVERRIDE!>override<!> fun key(): Int = 1
    <!NOTHING_TO_OVERRIDE!>override<!> fun value(): String = "x"
}

// TESTCASE NUMBER: 3
interface Holder<T> {
    val current: T
}

class MissingArgs : <!WRONG_NUMBER_OF_TYPE_ARGUMENTS!>Holder<!> {
    <!NOTHING_TO_OVERRIDE!>override<!> val current: Int = 1
}
