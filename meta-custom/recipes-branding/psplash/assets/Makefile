OUTPUT=../files/psplash-poky-img.h

all: $(OUTPUT)

clean:
	rm -f $(OUTPUT)
	

$(OUTPUT): custom-logo.png
	gdk-pixbuf-csource --macros $< | sed -e "s/MY_PIXBUF/POKY_IMG/g" -e "s/guint8/uint8/g" >$@

