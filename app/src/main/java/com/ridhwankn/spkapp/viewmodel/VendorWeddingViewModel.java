package com.ridhwankn.spkapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.ridhwankn.spkapp.model.Content;
import com.ridhwankn.spkapp.model.bean.DetailVendorBean;

import java.util.ArrayList;

public class VendorWeddingViewModel extends ViewModel {

    public ArrayList<DetailVendorBean> getDetail(){
        Gson gson = new Gson();
        Content content = gson.fromJson(result, Content.class);
        ArrayList<DetailVendorBean> detailVendorBean = content.getDetailVendor();
        return detailVendorBean;
    }

    String result = "{\n" +
            "\t\"detailVendor\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Wedding Factory\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"We build our relationship with all of our clients like we're best friends and we serve profesionally and give you with the fullest service for you for your once in a lifetime moments..\",\n" +
            "\t\t\t\"address\": \"Jl. Delima Raya no 34, Tanjung Duren Selatan 16720\",\n" +
            "\t\t\t\"location\": \"Jakarta.Bogor\",\n" +
            "\t\t\t\"service\": \"Photography.Videography.Hair & Makeup\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=14J3ni1JPR3m4ibaFUFmrqOnGfP8yfaIG\"\n" +
            "\t\t\t\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Alissha Bride\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Established in 2012, Alissha is known for its up to date gowns and spectacular quality of its dress. We served the best quality of our dresses, make-up, and photography. And now we also have venue, decoration, and Wedding Organizer & Entertainment (All-In Packages) Our highly-rated gowns designs are beloved by brides all across Greater Jakarta Area and our showroom is an oasis of ideas of wedding dream. And our All-In Packages will help you to get more affordable, more efficient, less worries !\",\n" +
            "\t\t\t\"address\": \"Jl. Bisma Tengah 2 No.21, RT.7/RW.9, Papanggo, Tj. Priok, Kota Jkt Utara, Daerah Khusus Ibukota Jakarta 14340\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Bridal\u200B.\u200BDress & Attire\u200B.\u200BHair & Makeup\u200B.\u200BPhotography\u200B.\u200BVenue\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=16XmKLipvH_Kjrs_mBQpK5lKzzCLCulBJ\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Too-lus\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Let the picture speak!\",\n" +
            "\t\t\t\"address\": \"Jalan HM Yasin no 31 Pejaten Timur, Jakarta Indonesia 12530\",\n" +
            "\t\t\t\"location\": \"Jakarta.Bogor\",\n" +
            "\t\t\t\"service\": \"Photography.Videography.\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1NazvojZDnVXmXgx8LhLN9G50RBVKy59X\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"AYANA Midplaza JAKARTA\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Conveniently located in the heart of Jakarta's vibrant Jalan Sudirman central business district, AYANA is conveniently located within a 45-minute drive to and from Soekarno Hatta International Airport, as well as 2km from Plaza Indonesia shopping mall. If you’re planning to host a wedding fit for the society pages, our professional team is happy to accommodate you in our stunning Grand Ballroom or one of our wedding venues.\",\n" +
            "\t\t\t\"address\": \"AYANA Midplaza JAKARTA\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Venue\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1RRK9xpXX9N6olal7T1j_xHIOcWqDOTWZ\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Weddingscape\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Weddingscape is exclusive kami mempersiapkan semuanya untuk hari besar pernikahan. dari satu pernikahan ke pernikahan selanjutnya, kami selalu menemukan senyum bahagia dari setiap pasangan dan keluarganya. kami ingin tidak hanya orang yang hadir pada saat itu yang merasakan aura kebahagiaan dari sebuah prosesi pernikahan. kami ingin setiap orang yang melihat kembali kejadian tersebut melalui sebuah foto atau video, ikut merasakan suasana bahagia ketika itu. with passion weddingscape\",\n" +
            "\t\t\t\"address\": \"Jl. Semangka 3 Rt. 13 Rw 09 No 55 Kel Jati Pulo Kec Palmerah Jakarta 11430\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Photography\u200B.\u200BVideography\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1GVX9mOlN9ZFSgbcddYeAgwEEXw7diJKJ\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Mosandy Esenway management\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"We would like to offer you our unforgettable and extraordinary Master of Ceremony ( MC ) and Entertainment performance on your event and special day. Mosandy Esenway Management is a MC and Entertainment Management consisting of talented people which established in 2013. We provide various characters of prime quality bilingual MC and high class quality of entertainment to make your event and special day memorable Let us maximize your event atmosphere and make it UNFORGETABLE \",\n" +
            "\t\t\t\"address\": \"by appointment\",\n" +
            "\t\t\t\"location\": \"Jakarta\u200B.\u200BSurabaya\u200B.\u200BBali\",\n" +
            "\t\t\t\"service\": \"Entertainment (MC)\u200B.\u200BEntertainment (Music)\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1QX3b0xIgLWYb9dgK-KqBi86Ig8eQtfM1\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"iLook ( Makeup & Couture )\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"We provide a special service with a touch of magic to make your special day even much more memorable. We are here to make you happy and pretty..\",\n" +
            "\t\t\t\"address\": \"Jl. Imam Malbud Jakarra Pusad, 11520\",\n" +
            "\t\t\t\"location\": \"Jakarta.Bali\",\n" +
            "\t\t\t\"service\": \"Hair & Makeup\u200B.\u200BDress & Attire\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1FtVVRQ1I4RvPUNhVdmhptq0G0FdwHz7C\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"GoFotoVideo\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Are you looking for a passionate team of photographers and videographers to capture your once-in-a-lifetime moment? GoFotoVideo is the answer! We are pre-wedding, wedding and commercial product photographer/ videographer based in Jakarta, Indonesia.\",\n" +
            "\t\t\t\"address\": \"Komplek BPK IV, Kebon Jeruk, Jakarta Barat 11530\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Photography\u200B.\u200BVideography\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1PW39xHjbV9iAzlUTsIIjt73Xi5fNcZ8p\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Etre Atelier\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Our atelier not only offers custom made service for wedding gowns, but also tailor made suits. We love to help you to look the best and be a one place for all - wedding gowns, kebaya, qipao, groom suits, beskap, nanshan. Every client is unique and we'd be more than happy to help you figure out which silhouette and details suit your body type and the occasions.\",\n" +
            "\t\t\t\"address\": \"PIK\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Dress & Attire\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=15NZgFjK4yj5HqvuL4ddOX1frDivdHEQ7\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Luxe Voir Enterprise\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"We provide professional MC and session player for Wedding, Event, Gathering, etc\",\n" +
            "\t\t\t\"address\": \"Kalideres, 11510\",\n" +
            "\t\t\t\"location\": \"Jakarta\u200B.\u200BBali\u200B.\u200BBandung\u200B.\u200BSurabaya\u200B.\u200BSingapore\",\n" +
            "\t\t\t\"service\": \"Entertainment (Music)\u200B.\u200BEntertainment (MC)\u200B.\u200BEntertainment (DJ)\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1rBrHyXnodGdrf-DymQR9z6Bv5pt4MxXo\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"The Right Two\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"We are a collective of dedicated and passionate souls, working hand in hand to turn your vision into a reality.\\nLove stories naturally leave us in awe; all the firsts and the bests.\\nLet your souls speak for your big day; no wedding would ever turn up the same.\",\n" +
            "\t\t\t\"address\": \"Jl. Pulau Saelus No. 82, 80223\",\n" +
            "\t\t\t\"location\": \"Jakarta.Bali\",\n" +
            "\t\t\t\"service\": \"Wedding Planning.\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1GH9YuAvym0x-5_zWqY0JW9UqNxrmg1iI\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Trickeffect\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Hello and welcome to Trickeffect \\nWe are a photography team based in Tangerang and Bali.\\nWedding Journalist | Prewedding or Postwedding | Candid\\nEvent Documentation | Cinematography | etc Journalist of Your Stories\",\n" +
            "\t\t\t\"address\": \"Office 99, Gedung EduCenter Lantai 2A Unit 24054 Jl. Sekolah Foresta No 8 BSD City Kelurahan Lengkong Kulon, Kecamatan Pagedangan Kab. Tangerang , Banten 15331 Indonesia\",\n" +
            "\t\t\t\"location\": \"Jakarta.Tangerang\",\n" +
            "\t\t\t\"service\": \"Photography\u200B.\u200BVideography\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1u_esg6sCdMYZxkEd2_TMyWh4uDiVcNV8\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Jas-ku.com\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"You’ve found your partner in life, now it’s time to find the right wedding suit to wear on your special day. Planning a wedding could be overwhelming, that's why having a reliable vendor that guarantees your satisfaction is essential. We are here to discuss your ideas for your wedding to get you the perfect suit as we know  the form and the fit of a suit is very important. For bespoke tailor made mens suit, wedding suit, custom shirts or tailored trousers, kindly contact our friendly team today!\",\n" +
            "\t\t\t\"address\": \"ITC Mangga Dua Lantai 3 Blok D76 14430\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Men's Formal Wear\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1gXz220mI7o_tzhEx_Lc8xDhgzfLBOjBz\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Barva Entertainment\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Music Entertainment\",\n" +
            "\t\t\t\"address\": \"Ampera Building, Jl. Ampera Raya 18b, Kemang NKF, Jakarta Selatan 12780\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Entertainment (Music)\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1o4ZIB-fyqgeKGOV6fLvrH45dFAKDjvtz\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Satrisca Makeup Artist\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Bali, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Menerima anda yang spesial di hari yang spesial\",\n" +
            "\t\t\t\"address\": \"Jl Nangka Gg Murai II 80239\",\n" +
            "\t\t\t\"location\": \"Bali\",\n" +
            "\t\t\t\"service\": \"Hair & Makeup\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=12wgPpAuVDLyM844PDsK0YO6LiZ7TA0nV\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Nouri Jewellery\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Malang, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Nouri Jewellery adalah Pusat Cincin Kawin Custom Palladium, Platinum, Emas Putih, dan Silver yang Sudah Berpengalaman Mengerjakan Ribuan Cincin Kawin Ribuan Pasangan Indonesia. \",\n" +
            "\t\t\t\"address\": \"Jl. Raya Ngijo No. 280 , Karangploso, Malang 65152\",\n" +
            "\t\t\t\"location\": \"Malang\",\n" +
            "\t\t\t\"service\": \"Jewelry\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=12Hmbs5JLJCK9yUTU5nRNWbv2Fn-zTtsP\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"KEYS Entertainment\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Founded by passionate musician David Obedianto, graduated from Academy of Art University and Indonesian Music Institute. He began his career as a session player and now he also works as a composer. KEYS Entertainment is supported by young professional and experienced musicians with formal music education background. We always strive to build your trust and our integrity with our commitment to always deliver the best performance in every moment.\",\n" +
            "\t\t\t\"address\": \"Kelapa Gading, Jakarta Utara 14240\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Entertainment (Music)\u200B.\u200BEntertainment (MC)\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1f_FfKs0Noxd4DmAfnl8xNIfyw7NQMd-P\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"ASA organizer\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"We are a thematic event planner. We embrace the unique character of each client. That's why for one occasion, we ever created fashion show within the grand entrance, on the other occasion we made a surprise flashmob for our bride, groom, all the guests, and  many more. We are mastering in organizing and creating a concept for all type of event. I hope we can think of an integrated concept for your wedding. Our JAKARTA & BALI team are ready to make your wish come true.\",\n" +
            "\t\t\t\"address\": \"Muara Karang Blok S4 selatan, no.6a, level 2, Jakarta Utara. 14450\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Wedding Planning\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1UFP-YMlO9zIi8q9ohwkMUqdD8-lufy4F\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"La'SEINE Function Hall\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Behind every couple is a story unlike any other. Your wedding day should be remembered because everything was just as you dreamed it would be but better. More importantly, it should be remembered because it was tailored exactly to your every unique want and need. The most important day of your life deserves perfection. Some things life is in a class of their own  red soles on stilettos, the signature weave on a clutch, or an iconic tweed suit. Your nuptials? By La’ Seine, naturally.\",\n" +
            "\t\t\t\"address\": \"Cyber 2 Tower 17/Fl Jl. HR. Rasuna Said Block X.5, Kav. 13 Jakarta 12950\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Venue\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1tGyiBTL745nO2d8qlLRT3iWCph8SoANV\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"nameVendor\": \"Moist Wedding Planner & Organizer\",\n" +
            "\t\t\t\"numStar\": 5,\n" +
            "\t\t\t\"city\": \"Jakarta, ID\",\n" +
            "\t\t\t\"aboutMe\": \"Let Us Be Your Wedding Best Part \",\n" +
            "\t\t\t\"address\": \"Cityloft Building Sudirman, Jl.K.H.Mas Mansyur, Jakarta Pusat 10220\",\n" +
            "\t\t\t\"location\": \"Jakarta\",\n" +
            "\t\t\t\"service\": \"Wedding Planning\",\n" +
            "\t\t\t\"image\": \"https://drive.google.com/uc?id=1z69WQq3yg4yBgPzHCb-LF8b91RYnQRj6\"\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";
}
