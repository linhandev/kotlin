// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: InterfaceConflict533 reports MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED; ConcreteMissing533 reports ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED
 */

// TESTCASE NUMBER: 1
interface Alpha533 {
    fun foo(): Int = 1
}

interface Beta533 {
    fun foo(): Int = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class InterfaceConflict533<!> : Alpha533, Beta533

// TESTCASE NUMBER: 2
abstract class AbstractBase533 {
    abstract fun required(): Int
}

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class ConcreteMissing533<!> : AbstractBase533()
