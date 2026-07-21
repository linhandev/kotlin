// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, builder-style-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: builder-style type inference — runtime buildList145 collects inferred element type
 */
// TESTCASE NUMBER: 1

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

fun box(): String {
    val list = build145 {
        add(1)
        add(2)
    }
    return if (list == listOf(1, 2)) "OK" else "NOK"
}
