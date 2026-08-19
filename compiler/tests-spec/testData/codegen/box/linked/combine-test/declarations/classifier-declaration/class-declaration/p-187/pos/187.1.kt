// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 187 -> sentence 187
 * PRIMARY LINKS: inheritance, overriding -> paragraph 187 -> sentence 187
 *                declarations, declaration-visibility -> paragraph 187 -> sentence 187
 *                inheritance, inheriting -> paragraph 187 -> sentence 187
 * NUMBER: 1
 * DESCRIPTION: override in a class declaration may widen visibility so a protected/internal open member becomes publicly callable
 */

// TESTCASE NUMBER: 1
open class Base {
    protected open fun f(): Int = 1
    fun read(): Int = f()
}

class Child : Base() {
    public override fun f(): Int = 2
}

// TESTCASE NUMBER: 2
open class Service {
    internal open fun ping(): String = "base"
    fun probe(): String = ping()
}

class PublicService : Service() {
    public override fun ping(): String = "public"
}

// TESTCASE NUMBER: 3
open class Holder {
    protected open val label: String = "base"
    fun banner(): String = "[$label]"
}

class PublicHolder : Holder() {
    public override val label: String = "shown"
}

fun box(): String {
    val child = Child()
    if (child.f() != 2) return "NOK: child-f"
    if (child.read() != 2) return "NOK: child-read"
    if (Base().read() != 1) return "NOK: base-read"
    if ((child as Base).read() != 2) return "NOK: base-ref-read"

    val service = PublicService()
    if (service.ping() != "public") return "NOK: service-ping"
    if (service.probe() != "public") return "NOK: service-probe"
    if (Service().probe() != "base") return "NOK: service-base"
    if ((service as Service).probe() != "public") return "NOK: service-ref"

    val holder = PublicHolder()
    if (holder.label != "shown") return "NOK: holder-label"
    if (holder.banner() != "[shown]") return "NOK: holder-banner"
    if (Holder().banner() != "[base]") return "NOK: holder-base"
    if ((holder as Holder).banner() != "[shown]") return "NOK: holder-ref"
    return "OK"
}
