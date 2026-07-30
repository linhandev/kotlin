// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: empty implementing class inherits all members when interface has only default function bodies (no abstract members)
 */

// TESTCASE NUMBER: 1
interface Marker {
    fun ping(): String = "ok"
    fun tag(): String = "marker"
}

class EmptyImpl : Marker

fun box(): String {
    if (EmptyImpl().ping() != "ok") return "NOK: empty-ping"
    if (EmptyImpl().tag() != "marker") return "NOK: empty-tag"
    val asMarker: Marker = EmptyImpl()
    if (asMarker.ping() != "ok") return "NOK: via-marker-ping"
    if (asMarker.tag() != "marker") return "NOK: via-marker-tag"
    if (EmptyImpl() !is Marker) return "NOK: is-marker"
    return "OK"
}
