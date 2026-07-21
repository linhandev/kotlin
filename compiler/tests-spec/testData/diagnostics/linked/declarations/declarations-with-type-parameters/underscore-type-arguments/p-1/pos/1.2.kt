// FIR_IDENTICAL
// LANGUAGE: +PartiallySpecifiedTypeArguments
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, underscore-type-arguments -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: a single underscore type argument can guide overload resolution and inference
 */

// TESTCASE NUMBER: 1
fun <T> foo(t: T): T = t

fun inferFromLiteral(): Int = foo<_>(42)

// TESTCASE NUMBER: 2
fun <T> mk(): T = TODO()

interface Task<T> {
    fun run(): T
}

class TextTask : Task<String> {
    override fun run(): String = "ok"
}

inline fun <reified S : Task<T>, T> execute(): T = mk<S>().run()

fun inferWithReified(): String = execute<TextTask, _>()
