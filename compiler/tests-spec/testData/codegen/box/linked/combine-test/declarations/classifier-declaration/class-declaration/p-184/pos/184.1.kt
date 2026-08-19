// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 184 -> sentence 184
 * PRIMARY LINKS: inheritance, overriding -> paragraph 184 -> sentence 184
 *                type-system, subtyping, subtyping-rules -> paragraph 184 -> sentence 184
 *                inheritance, inheriting -> paragraph 184 -> sentence 184
 * NUMBER: 1
 * DESCRIPTION: overriding may return a more specific type than the open base; call sites typed as the base still receive the subclass runtime value
 */

// TESTCASE NUMBER: 1
open class Factory {
    open fun create(): Number = 1
}

class IntFactory : Factory() {
    override fun create(): Int = 2
}

// TESTCASE NUMBER: 2
open class TextSource {
    open fun text(): CharSequence = "base"
}

class StringSource : TextSource() {
    override fun text(): String = "child"
}

// TESTCASE NUMBER: 3
open class BoxMaker {
    open fun make(): List<Any> = listOf(1)
}

class StringBoxMaker : BoxMaker() {
    override fun make(): List<String> = listOf("ok")
}

fun box(): String {
    val factory: Factory = IntFactory()
    val n: Number = factory.create()
    if (n != 2) return "NOK: number-value"
    if (n !is Int) return "NOK: number-is-int"
    if (IntFactory().create() != 2) return "NOK: int-factory"
    if (Factory().create() != 1) return "NOK: factory-base"

    val source: TextSource = StringSource()
    val cs: CharSequence = source.text()
    if (cs.toString() != "child") return "NOK: text-value"
    if (cs !is String) return "NOK: text-is-string"
    if (StringSource().text().length != 5) return "NOK: string-length"

    val maker: BoxMaker = StringBoxMaker()
    val list: List<Any> = maker.make()
    if (list != listOf("ok")) return "NOK: list-value"
    if (list.first() !is String) return "NOK: list-elem-string"
    return "OK"
}
