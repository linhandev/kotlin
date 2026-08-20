// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 283 -> sentence 283
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 283 -> sentence 283
 *                inheritance, overriding -> paragraph 283 -> sentence 283
 *                inheritance, inheriting -> paragraph 283 -> sentence 283
 * NUMBER: 1
 * DESCRIPTION: override cannot narrow visibility (CANNOT_WEAKEN_ACCESS_PRIVILEGE); covers abstract protected→private, generic protected→private, and public→protected property; contrasts with p-186 open-class/public→internal/public→protected set and with next-point widening success
 */

// TESTCASE NUMBER: 1
abstract class TokenBase {
    protected abstract fun token(): Int
}

class TokenChild : TokenBase() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE, INCOMPATIBLE_MODIFIERS!>private<!> <!INCOMPATIBLE_MODIFIERS!>override<!> fun token(): Int = 2
}

// TESTCASE NUMBER: 2
abstract class CodeBase<T> {
    protected abstract fun code(): T
}

class CodeChild : CodeBase<Int>() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE, INCOMPATIBLE_MODIFIERS!>private<!> <!INCOMPATIBLE_MODIFIERS!>override<!> fun code(): Int = 2
}

// TESTCASE NUMBER: 3
open class LabelBase {
    public open val label: String = "base"
}

class LabelChild : LabelBase() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>internal<!> override val label: String = "child"
}
