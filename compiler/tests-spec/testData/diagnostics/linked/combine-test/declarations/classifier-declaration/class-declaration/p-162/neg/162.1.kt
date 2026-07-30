// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 162 -> sentence 162
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 162 -> sentence 162
 *                inheritance, inheriting -> paragraph 162 -> sentence 162
 * NUMBER: 1
 * DESCRIPTION: subclass constructor delegation must supply required superclass primary constructor arguments; empty or incomplete Base() calls fail in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child : Base<!NO_VALUE_FOR_PARAMETER!>()<!>

// TESTCASE NUMBER: 2
open class PairBase(val first: String, val second: Int)

class Incomplete : PairBase<!NO_VALUE_FOR_PARAMETER, NO_VALUE_FOR_PARAMETER!>()<!>

// TESTCASE NUMBER: 3
interface Marker

open class Sized(val n: Int, val tag: String)

class Mixed : Sized<!NO_VALUE_FOR_PARAMETER, NO_VALUE_FOR_PARAMETER!>()<!>, Marker
