// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 96 -> sentence 96
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 96 -> sentence 96
 *                declarations, property-declaration -> paragraph 96 -> sentence 96
 * NUMBER: 1
 * DESCRIPTION: secondary constructor cannot declare val or var property parameters in class declaration
 */

// TESTCASE NUMBER: 1
class Bad {
    constructor(<!VAL_OR_VAR_ON_SECONDARY_CONSTRUCTOR_PARAMETER!>val<!> x: Int) : this()
}
