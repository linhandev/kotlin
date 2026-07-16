// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: overriding declarations may use an equally or less visible modifier
 */

// TESTCASE NUMBER: 1
open class OpenBase {
    protected open fun guarded() {}
}

class Widen : OpenBase() {
    public override fun guarded() {}
}

// TESTCASE NUMBER: 2
open class InternalBase {
    internal open fun service() {}
}

class InternalImpl : InternalBase() {
    public override fun service() {}
}
