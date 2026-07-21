// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: implicit default primary constructor when no constructors declared
 */

// TESTCASE NUMBER: 1
open class Base

class POKO : Base()

class NotQuitePOKO : Base {
    constructor() : super()
}
