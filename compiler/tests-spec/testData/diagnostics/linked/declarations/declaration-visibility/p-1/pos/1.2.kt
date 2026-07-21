// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: visibility modifiers may be declared explicitly and compose with inheritance
 */

// TESTCASE NUMBER: 1
public fun explicitlyPublic(): String = "ok"

// TESTCASE NUMBER: 2
internal fun moduleInternal(): Int = 42

fun useInternal(): Int = moduleInternal()

// TESTCASE NUMBER: 3
open class VisibleBase {
    protected open val label: String = "base"
}

class VisibleDerived : VisibleBase() {
    override val label: String = "derived"
}
