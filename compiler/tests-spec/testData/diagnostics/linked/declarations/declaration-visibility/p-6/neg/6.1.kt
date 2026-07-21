// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: overriding declarations cannot use a more visible modifier than required
 */

// TESTCASE NUMBER: 1
open class OpenBase {
    protected open fun guarded() {}
}

class BadDerived : OpenBase() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE, INCOMPATIBLE_MODIFIERS!>private<!> <!INCOMPATIBLE_MODIFIERS!>override<!> fun guarded() {}
}

// TESTCASE NUMBER: 2
open class PublicBase {
    public open fun api() {}
}

class WeakerOverride : PublicBase() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>protected<!> override fun api() {}
}
