// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 241 -> sentence 241
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 241 -> sentence 241
 *                inheritance, inheriting -> paragraph 241 -> sentence 241
 * NUMBER: 1
 * DESCRIPTION: a class cannot implement an interface by fixing a type argument that violates the interface type-parameter upper bound; contrasts with p-240 matching Number subtypes and with class-constructor bound violations in p-6/p-132
 */

// TESTCASE NUMBER: 1
interface NumBox<T : Number> {
    val v: T
}

class BadStringBox : NumBox<<!UPPER_BOUND_VIOLATED!>String<!>> {
    override val v: String = ""
}

// TESTCASE NUMBER: 2
interface Scaled<T : Number> {
    fun raw(): T
}

class BadCharScaled : Scaled<<!UPPER_BOUND_VIOLATED!>Char<!>> {
    override fun raw(): Char = 'x'
}

// TESTCASE NUMBER: 3
interface Measurable<T : Number> {
    fun amount(): T
}

class BadBoolMeasure : Measurable<<!UPPER_BOUND_VIOLATED!>Boolean<!>> {
    override fun amount(): Boolean = false
}
