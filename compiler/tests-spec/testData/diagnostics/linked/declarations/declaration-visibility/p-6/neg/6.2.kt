// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: override visibility must be compatible with all inherited declarations
 */

// TESTCASE NUMBER: 1
open class InternalBase {
    internal open fun service() {}
}

interface PublicService {
    fun service()
}

class IncompatibleOverride : InternalBase(), PublicService {
    <!CANNOT_CHANGE_ACCESS_PRIVILEGE!>protected<!> override fun service() {}
}

// TESTCASE NUMBER: 2
open class ProtectedBase {
    protected open fun guarded() {}
}

class NarrowOverride : ProtectedBase() {
    <!CANNOT_CHANGE_ACCESS_PRIVILEGE!>internal<!> override fun guarded() {}
}
