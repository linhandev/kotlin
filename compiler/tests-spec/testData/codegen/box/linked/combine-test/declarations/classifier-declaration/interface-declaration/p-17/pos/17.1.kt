// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 17 -> sentence 17
 *                declarations, declarations-with-type-parameters -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: generic interface default function can use type parameter in return type
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun empty(): T? = null
}

class IntBoxImpl : Box<Int>

class StringBoxImpl : Box<String> {
    override fun empty(): String? = "value"
}

fun box(): String {
    if (IntBoxImpl().empty() != null) return "NOK: int-default-null"
    if (StringBoxImpl().empty() != "value") return "NOK: string-override"
    val asBox: Box<Int> = IntBoxImpl()
    if (asBox.empty() != null) return "NOK: via-interface-null"
    return "OK"
}
