// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 263 -> sentence 263
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 263 -> sentence 263
 *                inheritance, inheriting -> paragraph 263 -> sentence 263
 * NUMBER: 1
 * DESCRIPTION: a top-level private class is usable within the same file (via private clients or by reading members from public functions); contrasts with p-262 internal same-module and with next-point illegal top-level protected
 */

// TESTCASE NUMBER: 1
private class Hidden(val id: Int = 1)

private class Client {
    fun make(): Hidden = Hidden(1)
}

fun hiddenId(): Int = Hidden(1).id

fun viaClient(): Int = Client().make().id

// TESTCASE NUMBER: 2
private open class HiddenBase(val label: String)

private class HiddenChild(label: String, val extra: Int) : HiddenBase(label)

private fun makeChild(): HiddenChild = HiddenChild("h", 2)

fun childExtra(): Int = makeChild().extra

// TESTCASE NUMBER: 3
private class HiddenBox<T>(val value: T)

private fun makeBox(): HiddenBox<String> = HiddenBox("ok")

fun boxValue(): String = makeBox().value

fun box(): String {
    if (hiddenId() != 1) return "NOK: hidden-id"
    if (viaClient() != 1) return "NOK: via-client"
    val h: Hidden = Client().make()
    if (h.id != 1) return "NOK: local-hidden"

    if (makeChild().label != "h") return "NOK: child-label"
    if (childExtra() != 2) return "NOK: child-extra"
    val asBase: HiddenBase = makeChild()
    if (asBase.label != "h") return "NOK: via-base"

    if (makeBox().value != "ok") return "NOK: box"
    if (boxValue() != "ok") return "NOK: box-value"
    val asBox: HiddenBox<String> = makeBox()
    if (asBox.value != "ok") return "NOK: via-box"
    return "OK"
}
