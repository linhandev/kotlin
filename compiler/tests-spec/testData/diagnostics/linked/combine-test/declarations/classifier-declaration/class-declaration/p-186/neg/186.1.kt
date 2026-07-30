// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 186 -> sentence 186
 * PRIMARY LINKS: inheritance, overriding -> paragraph 186 -> sentence 186
 *                declarations, declaration-visibility -> paragraph 186 -> sentence 186
 *                inheritance, inheriting -> paragraph 186 -> sentence 186
 * NUMBER: 1
 * DESCRIPTION: override in a class declaration cannot narrow visibility of an open superclass member (CANNOT_WEAKEN_ACCESS_PRIVILEGE)
 */

// TESTCASE NUMBER: 1
open class Base {
    protected open fun f(): Int = 1
}

class Child : Base() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE, INCOMPATIBLE_MODIFIERS!>private<!> <!INCOMPATIBLE_MODIFIERS!>override<!> fun f(): Int = 2
}

// TESTCASE NUMBER: 2
open class PublicBase {
    public open fun api(): Int = 1
}

class WeakerOverride : PublicBase() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>internal<!> override fun api(): Int = 2
}

// TESTCASE NUMBER: 3
open class Holder {
    public open val label: String = "base"
}

class HiddenHolder : Holder() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>protected<!> override val label: String = "child"
}
