// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 167 -> sentence 167
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 167 -> sentence 167
 *                inheritance, inheriting -> paragraph 167 -> sentence 167
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 167 -> sentence 167
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 167 -> sentence 167
 * NUMBER: 1
 * DESCRIPTION: constructor arguments delegated to a generic superclass must match the declared type arguments in the class declaration
 */

// TESTCASE NUMBER: 1
open class Box<T>(val v: T)

class BadStringBox : Box<String>(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)

// TESTCASE NUMBER: 2
open class PairBox<A, B>(val first: A, val second: B)

class BadPair : PairBox<String, Int>(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>0<!>, <!TYPE_MISMATCH!>"x"<!>)

// TESTCASE NUMBER: 3
open class Holder<T>(val item: T)

class BadHolder(seed: Int) : Holder<String>(<!TYPE_MISMATCH!>seed<!>)
