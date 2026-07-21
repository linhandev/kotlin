// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, builder-style-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: builder-style type inference — single lambda receiver inferred from body without BuilderInference annotation
 * HELPERS: checkType
 */

class Builder145<T> {
    private val items = mutableListOf<T>()
    fun add(t: T) {
        items += t
    }
    fun result(): List<T> = items
}

fun <S> build145(builder: Builder145<S>.() -> Unit): List<S> {
    val b = Builder145<S>()
    b.builder()
    return b.result()
}

// TESTCASE NUMBER: 1
fun case_1(): List<Int> {
    val result = build145 { add(42) }
    checkSubtype<List<Int>>(result)
    return result
}
